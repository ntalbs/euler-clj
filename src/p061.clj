(ns p061
  (:require [util :refer (infix)]))

(defn- p [k n]
  (condp = k
    3 (infix ((n * (n + 1)) / 2))
    4 (infix (n * n))
    5 (infix ((n * ((3 * n) - 1)) / 2))
    6 (infix (n * ((2 * n) - 1)))
    7 (infix ((n * ((5 * n) - 3)) / 2))
    8 (infix (n * ((3 * n) - 2)))))

(defn- s [k]
  (->> (iterate inc 1)
       (map #(p k %))
       (drop-while #(< % 1000))
       (take-while #(< % 10000))))

(defn- ps [k]
  (->> (s k)
       (map (fn [n] {:k k, :n n}))))

(defn- first-2-digits [n] (quot n 100))
(defn- last-2-digits [n] (rem n 100))

(def M
  (->> (range 3 9)
       (mapcat ps)
       (group-by (fn [e] (first-2-digits (e :n))))))

(defn- chain [ps]
  (for [e ps, t (M (last-2-digits (:n (last e))))] (conj e t)))

(defn solve []
  (->> (map vector (ps 8))
       (chain)
       (chain)
       (chain)
       (chain)
       (chain)
       (chain)
       (filter #(= (first %) (last %))) ; check cycle
       (filter (fn [e] (= #{3 4 5 6 7 8} (set (map #(% :k) e)))))
       first rest
       (map #(:n %))
       (apply +)))
