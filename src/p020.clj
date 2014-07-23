;; #020
;; sum of the digits in the number 100!

(ns p020
  (:require [util :refer [digits factorial]]))

(defn p020 []
  (->> (factorial 100)
       digits
       (apply +)))

(defn solve []
  (time (println (p020))))
