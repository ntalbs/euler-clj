;; #041

(use '[util :only (prime?)]
     '[clojure.contrib.combinatorics :only (permutations)])

(def ds (range 1 10))

(defn investigate [v]
  (->> (permutations (reverse v))
       (map #(Integer/parseInt (apply str %)))
       (filter prime?)
       (take 1)))

(defn p041 []
  (flatten (->> (range 9 0 -1)
                (map #(range 1 (inc %)))
                (map investigate)
                (drop-while empty?)
                (take 1))))

(time (println (p041)))
