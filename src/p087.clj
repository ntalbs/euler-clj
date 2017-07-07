(ns p087
  (:require [clojure.contrib.lazy-seqs :refer [primes]]))

(defn- calc [a b c]
  (+ (* a a) (* b b b) (* c c c c)))

(defn- nth-root [n x]
  (int (Math/pow x (/ 1.0 n))))

(def ps1 (take-while #(< % (nth-root 2 50000000)) primes))
(def ps2 (take-while #(< % (nth-root 3 50000000)) primes))
(def ps3 (take-while #(< % (nth-root 4 50000000)) primes))

(defn solve []
  (->> (for [a ps1, b ps2, c ps3] (calc a b c))
       (filter #(< % 50000000))
       set
       count))
