(ns p087
  (:require [clojure.contrib.lazy-seqs :refer [primes]]))

(defn calc [a b c]
  (+ (* a a) (* b b b) (* c c c c)))

(def ps1 (take-while #(< % 7071) primes)) ; pow(50,000,000, 1/2)
(def ps2 (take-while #(< %  368) primes)) ; pow(50,000,000, 1/3)
(def ps3 (take-while #(< %   84) primes)) ; pow(50,000,000, 1/4)

(defn solve []
  (->> (for [a ps1, b ps2, c ps3] (calc a b c))
       (filter #(< % 50000000))
       distinct
       count))
