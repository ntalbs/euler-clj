(ns p051
  (:require [clojure.contrib.lazy-seqs :refer [primes]]
            [clojure.string :as s]
            [util :refer [digits prime?]]))

(defn check [n]
  (map count
       (for [m [0 1 2]]
         (for [x (map #(* n %) (range 10)) :when (< 0 (mod (+ m x) 3))]
           [m (+ m x)]))))

(defn digit-repeated-three-times? [ds d]
  (->> ds
       (filter #(= d %))
       count
       (= 3)))

(defn three-times-repeated-digit [n]
  (let [ds (butlast (digits n))]
    (cond (digit-repeated-three-times? ds 0) 0
          (digit-repeated-three-times? ds 1) 1
          (digit-repeated-three-times? ds 2) 2)))

(defn eight-prime-family? [p rd]
  (let [p (str p) rd (str rd)]
    (->> (for [i (range 10)] (s/replace p rd (str i)))
         (map #(Integer/parseInt %))
         (filter #(< (count p) (inc (Math/log10 %))))
         (filter prime?)
         count
         (= 8))))

(defn solve []
  (->> primes
       (drop-while #(< % 56003))
       (map (fn [p] [p (three-times-repeated-digit p)]))
       (filter second)
       (filter (fn [[p rd]] (eight-prime-family? p rd)))
       ffirst))
