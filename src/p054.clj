;; #054

(def numbers [\A \2 \3 \4 \5 \6 \7 \8 \9 \T \J \Q \K \A])

(def ranks
  {\2 2, \3 3, \4 4, \5 5, \6 6, \7 7, \8 8, \9 9, \T 10, \J 11, \Q 12, \K 13, \A 1})

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

(defn flush? [cards]
  (->> (map last cards)
       (group-by identity)
       (count)
       (= 1)))

(defn straight? [cards]
  (contains? straight (into #{} (map first cards))))

(defn three-card? [cards]
  (->> (map first cards)
       (group-by identity)
       (filter (fn [[k v]] (= (count v) 3)))
       (empty?)
       (not)))

(defn two-pairs? [cards]
  (= 2 (count (->> (map first cards)
                   (group-by identity)
                   (filter (fn [[k v]] (= (count v) 2)))))))

(defn one-pair? [cards]
  (= 1 (count (->> (map first cards)
                   (group-by identity)
                   (filter (fn [[k v]] (= (count v) 2)))))))

(defn higest-value [cards]
  (->> (map first cards)
       (map ranks)
       (apply max)))

(defn score [cards]
  (cond (royal-flush? cards) 100
        (straight-flush? cards) 90
        (four-card? cards) 80
        (full-house? cards) 70
        (flush? cards) 60
        (straight? cards) 50
        (three-card? cards) 40
        (two-pairs? cards) 30
        (one-pair? cards) 20
        :else (higest-value cards)))

(defn winner [p1-cards p2-cards]
  (let [p1-score (score p1-cards) p2-score (score p2-cards)
        p1-higest (higest-value p1-cards) p2-highest (higest-value p2-cards)]
    (cond (> p1-score p2-score) :p1
          (< p1-score p2-score) :p2
          :else (cond (> p1-higest p2-highest) :p1
                      (< p1-higest p2-highest) :p2
                      :else nil))))
(defn p054 []
  (->> (map (fn [g] (winner (g :p1) (g :p2))) games)
       (group-by identity)
       (map (fn [[k v]] [k (count v)]))))

(time (println (p054)))
