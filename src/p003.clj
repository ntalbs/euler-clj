;; #003
;; largest prime factor of 600851475143

(ns p003
  (:require [util :refer [factorize]]))

(def N 600851475143)

(defn p003 []
  (->> (factorize N)
       (map first)
       (apply max)))

(defn solve []
  (time (println (p003))))
