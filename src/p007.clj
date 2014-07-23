;; #007
;; 10,001st prime number?

(ns p007
  (:require [util :refer [prime?]]))

(defn p007 []
  (first (drop 10000 (filter prime? (iterate inc 2)))))

(defn solve []
  (time (println (p007))))
