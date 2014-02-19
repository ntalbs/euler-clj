;; #037
;; Find the sum of the only eleven primes
;; that are both truncatable from left to right and right to left.

(use '[clojure.contrib.lazy-seqs :only [primes]]
     '[util :only [parse-int prime? digits]])

(defn to-int [digits]
  (parse-int (apply str digits)))

(defn truncated-nums
  "Returns the sequence of numbers which are truncated from left and right."
  [num]
  (let [digits (digits num) n (count digits)]
    (->> (for [i (range n)]
           [(to-int (drop i digits)) (to-int (take (inc i) digits))])
         flatten
         sort)))

(defn all-prime? [nums]
  (= (count nums)
     (count (take-while prime? nums))))

(defn p037 []
  (->> (drop 4 primes) ; drop 2, 3, 5, 7
       (filter  (fn [n] (all-prime? (truncated-nums n))))
       (take 11)
       (apply +)))

(time (println (p037)))
