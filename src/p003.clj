;; #003
;; largest prime factor of 600851475143

(ns p003
  (:require [util :refer [factorize]]))

(def N 600851475143)

(defn solve []
  (->> (factorize N)
       (map first)
       (apply max)))
