(ns p053
  (:require [util :refer [factorial]]))

;; (defn c [n r]
;;   (/ (factorial n) (*' (factorial r) (factorial (- n r)))))

;; (defn c [n r]
;;   (let [nu (range 1 (inc n))
;;         de (concat (range 1 (inc r)) (range 1 (inc (- n r))))]
;;     (->> (map / nu de)
;;          (reduce *))))

(defn c [n r]
  (let [nu (range (inc (- n r)) (inc n))
        de (range 1 (inc r))]
    (->> (map / nu de)
         (reduce *))))

(defn solve []
  (->> (for [n (range 1 101) r (range 1 101)] (c n r))
       (filter #(>= % 1000000))
       count))
