;; #015
;; number of routes through 20x20 grid

(ns p015
  (:require [util :refer [factorial]]))

(def f factorial)

(defn p015 []
  (let [f40 (f 40) f20 (f 20)]
    (/ f40 (*' f20 f20))))

(defn solve []
  (time (println (p015))))
