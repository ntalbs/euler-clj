;; #050
;; Which prime, below one-million, can be written
;; as the sum of the most consecutive primes?

(ns p050
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [util :refer [prime?]]))

(def csp "cumulative sums of primes"
  (->> (reductions + primes)
       (take-while #(< % 1000000))))

(defn p050 []
  (->> (for [s (conj csp 0)] (map #(- % s) csp))
       flatten
       (filter pos?)
       (filter prime?)
       (apply max)))

(defn solve []
  (time (println (p050))))
