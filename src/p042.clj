(ns p042)

(defn- t [n]
  (-> (+ n 1) (* n) (/ 2)))

(def t-set
  (set (map t (range 1 100))))

(defn- triangle-number? [n]
  (contains? t-set n))

(def words
  (clojure.string/split (slurp "data/words.txt") #"\"(,\")?"))
(defn- word-value [word]
  (->> word
       (map #(- (int %) 64))
       (apply +)))

(defn p042 []
  (->> (map word-value words)
       (filter triangle-number?)
       count))

(defn solve []
  (time (println (p042))))
