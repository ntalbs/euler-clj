;; #026
;; Find the value of d  1000 for which 1/d contains the longest recurring cycle
;; in its decimal fraction part.

(ns p026)

(defn qs [n]
  "Returns the unit fraction of denominator n
   in the form of [n [quotient digits] (# of digits of recurring cycle)].
   Though the second element of the vector also counts non recurring part,
   it doesn't affect the answer."
  (loop [r 10 r-acc #{} q-acc []]
    (cond (zero? r) [n q-acc 0]                       ; no recurring cycle
          (contains? r-acc r) [n q-acc (count q-acc)] ; recurring
          :else (recur (* 10 (rem r n)) (conj r-acc r) (conj q-acc (quot r n))))))

(defn p026 []
  (->> (range 1 (inc 1000))
       (map (fn [n] (qs n)))
       (apply max-key (fn [[n _ cnt]] cnt))
       first))

(defn solve []
  (time (println (p026))))
