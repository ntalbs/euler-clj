;; #046

(ns p046
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [clojure.set :refer [difference]]
            [util :refer [prime?]]))

(def limit 10000)

(def ps (take-while #(< % limit) primes))

(def odd-composite-nums
  (->> (range 3 limit 2)
       (filter #(not (prime? %)))
       (into #{})))

(def goldbach-nums
  (into #{} (for [p ps n (range 1 (Math/sqrt limit))] (+ p (* 2 n n)))))

(defn p046 []
  (apply min (difference odd-composite-nums goldbach-nums)))

(defn solve []
  (time (println (p046))))
