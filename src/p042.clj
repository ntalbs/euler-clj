(ns p042)

(defn- t [n]
  (-> (+ n 1) (* n) (/ 2)))

(def t-set
  (set (map t (range 1 100))))

(defn- triangle-number? [n]
  (contains? t-set n))

(defn- word-value [word]
  (->> word
       (map #(- (int %) 64))
       (apply +)))

(defn solve []
  (->> (clojure.string/split (slurp "data/words.txt") #"\"(,\")?")
       (map word-value)
       (filter triangle-number?)
       count))
