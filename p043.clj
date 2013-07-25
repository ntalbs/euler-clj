;; #043

(use '[clojure.contrib.combinatorics :only (permutations)])

(defn check [ds]
  "Returns the number whose digits are ds if the number satisfies the condition.
   Otherwise returns 0."
  (let [ps [2 3 5 7 11 13 17]
        cnt (->> (partition 3 1 ds)
                 (drop 1)
                 (map #(Long/parseLong (apply str %)))
                 (map vector ps)
                 (filter (fn [[p n]] (= 0 (rem n p))))
                 count)]
    (if (= cnt (count ps)) (Long/parseLong (apply str ds)) 0)))

(defn p043 []
  (->> (permutations (range 10))
       (map check)
       (apply +)))

; Takes too long. Need to be improved.
(time (println (p043)))
