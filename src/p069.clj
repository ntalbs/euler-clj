;; #069
;; Find the value of n  1,000,000 for which n/φ(n) is a maximum.

(use '[clojure.contrib.lazy-seqs :only (primes)])

(defn p069 []
  (->> (reductions * primes)
       (take-while #(< % 1000000))
       last))

(time (println (p069)))
