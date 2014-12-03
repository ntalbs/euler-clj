;; #31과 거의 동일한 문제. #31 풀이 소스를 약간 수정해서 풀었음.

(ns p077
    (:require [clojure.contrib.lazy-seqs :refer [primes]]))

(def limit 100)

(def i->p
  (into {} (take limit (map-indexed (fn [i p] [(inc i) p]) primes))))

(defn count-sum-of-primes [num]
  (letfn [(cc [num ps-index]
            (cond (= num 0) 1
                  (or (< num 0) (= ps-index 0)) 0
                  :else (+ (cc num (dec ps-index))
                           (cc (- num (i->p ps-index)) ps-index))))]
    (cc num limit)))

(defn p077 []
  (->> (iterate inc 10)
       (map (fn [n] [n (count-sum-of-primes n)]))
       (drop-while (fn [[_ c]] (<= c 5000)))
       ffirst))

(defn solve []
  (time (println (p077))))
