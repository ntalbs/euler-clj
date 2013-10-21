;; #054

(def numbers [\A \2 \3 \4 \5 \6 \7 \8 \9 \T \J \Q \K \A])

(def ranks
  {\2 2, \3 3, \4 4, \5 5, \6 6, \7 7, \8 8, \9 9, \T 10, \J 11, \Q 12, \K 13, \A 14})

(def straight
  (into #{} (map #(into #{} %) (partition 5 1 numbers))))

(def games
  (->> (clojure.string/split (slurp "data/poker.txt") #"\r\n")
       (map #(clojure.string/split % #" "))
       (map (fn [g] {:p1 (take 5 g) :p2 (drop 5 g)}))))

(defn straight-nums? [nums]
  (contains? straight (into #{} nums)))

(defn royal-flush? [cards]
  (let [numbers (apply str (sort (map first cards)))
        suits (sort (map last cards))]
    (and (= "AJKQT" numbers) (= 1 (count (partition-by identity suits))))))

(defn straight-flush? [cards]
  (let [numbers (apply str (sort (map first cards)))
        suits (sort (map last cards))]
    (and (straight-nums? numbers) (= 1 (count (partition-by identity suits))))))

(defn four-card? [cards]
  (->> (map first cards)
       (group-by identity)
       (filter (fn [[k v]] (>= (count v) 4)))
       (empty?)
       (not)))

(defn full-house? [cards]
  (let [v (->> (map first cards)
               (group-by identity)
               (map (fn [[k v]] (count v))))]
    (or (= [2 3] v) (= [3 2] v))))

;;; no royal-flush, straight-flush, four-cards, full-house

(defn highest [nums]
  (reduce max (map ranks nums)))

(defn flush? [cards]
  (if (->> (map last cards)
           (group-by identity)
           (count)
           (= 1))
    (highest (map first cards))))

(defn straight? [cards]
  (if (contains? straight (into #{} (map first cards)))
    (highest (map first cards))))

(def not-empty? (comp not empty?))

(defn three-card? [cards]
  (let [three-card (->> (map first cards)
                        (group-by identity)
                        (filter (fn [[k v]] (= (count v) 3))))]
    (if (not-empty? three-card)
      (ranks (first (first three-card))))))

(defn two-pairs? [cards]
  (let [pairs (->> (map first cards)
                   (group-by identity)
                   (filter (fn [[k v]] (= (count v) 2))))]
    (if (= 2 (count pairs))
      (apply max (map ranks (map first pairs))))))

(defn one-pair? [cards]
  (let [pairs (->> (map first cards)
                   (group-by identity)
                   (filter (fn [[k v]] (= (count v) 2))))]
    (if (= 1 (count pairs))
      (apply max (map ranks (map first pairs))))))

(defn higest-value [cards]
  (->> (map first cards)
       (map ranks)
       (apply max)))

(defn score [cards]
  (cond (royal-flush? cards) [100 1]
        (straight-flush? cards) [90 1]
        (four-card? cards) [80 1]
        (full-house? cards) [70 1]
        (flush? cards) [60 (flush? cards)]
        (straight? cards) [50 (straight? cards)]
        (three-card? cards) [40 (three-card? cards)]
        (two-pairs? cards) [30 (two-pairs? cards)]
        (one-pair? cards) [20 (one-pair? cards)]
        :else [0 (higest-value cards)]))

(defn winner [p1-cards p2-cards]
  (let [p1 (score p1-cards) p2 (score p2-cards)
        p1-score (first p1) p2-score (first p2)
        p1-higest (second p1) p2-highest (second p2)]
    (cond (> p1-score p2-score) :p1
          (< p1-score p2-score) :p2
          :else (cond (> p1-higest p2-highest) :p1
                      (< p1-higest p2-highest) :p2
                      :else nil))))
(defn p054 []
  (->> (map (fn [g] (winner (g :p1) (g :p2))) games)
       (group-by identity)
       :p1
       count))

(time (println (p054)))
