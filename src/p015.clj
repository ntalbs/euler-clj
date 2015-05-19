;; number of routes through 20x20 grid

(ns p015
  (:require [util :refer [factorial]]))

(defn solve []
  (let [f40 (factorial 40) f20 (factorial 20)]
    (/ f40 (*' f20 f20))))
