(ns p035
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [util :refer [parse-int digits prime? pow]]))

(defn- circular-nums [n]
  (let [ds (digits n) len (count ds)]
    (->> (cycle ds)
         (partition len 1)
         (take len)
         (map #(parse-int (apply str %))))))

(defn- all-prime? [coll]
  (every? prime? coll))

(defn solve0 []
  (->> primes
       (take-while #(< % 1000000))
       (filter (fn [n] (all-prime? (circular-nums n))))
       count))

(defn- cycle1 [n p]
  (let [i (quot n 10) l (rem n 10)]
    (+ (* l (pow 10 p)) i)))

(defn- circular-prime1? [n]
  (let [p (int (Math/log10 n))]
    (loop [n n, cnt p]
      (cond
        (= 0 cnt) (prime? n)
        (prime? n) (recur (cycle1 n p) (dec cnt))
        :else false))))

(defn- circular-prime2? [n] ;; 처음 전달된 n은 prime이라고 가정
  (let [p (int (Math/log10 n))]
    (loop [n (cycle1 n p), cnt (dec p)]
      (cond
        (<= cnt 0) (prime? n)
        (prime? n) (recur (cycle1 n p) (dec cnt))
        :else false))))

(defn solve1 []
  (->> primes
       (take-while #(< % 1000000))
       (filter circular-prime1?)
       count))

(defn solve2 []
  (->> primes
       (take-while #(< % 1000000))
       (filter circular-prime2?)
       count))

(defn solve[]
  (solve2))
