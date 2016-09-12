;; #31과 거의 동일한 문제. #31 풀이 소스를 약간 수정해서 풀었음.

(ns p077
    (:require [clojure.contrib.lazy-seqs :refer [primes]]))

(def limit 100)

(def i->p
  (into {} (take limit (map-indexed (fn [i p] [(inc i) p]) primes))))

(defn- cc [n i]
  (cond (= n 0) 1
        (< n 0) 0
        (= i 0) 0
        :else (+ (cc n (dec i)) (cc (- n (i->p i)) i))))

(defn solve []
  (->> (iterate inc 2)
       (map (fn [n] [n (cc n limit)]))
       (drop-while (fn [[_ c]] (<= c 5000)))
       ffirst))
