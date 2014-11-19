;; #061

(ns p061
  (:require [util :refer (infix)]))

(defn p [k n]
  (cond (= k 3) (infix ((n * (n + 1)) / 2))
        (= k 4) (* n n)
        (= k 5) (infix ((n * ((3 * n) - 1)) / 2))
        (= k 6) (infix (n * ((2 * n) - 1)))
        (= k 7) (int (infix (n * (((5 * n) - 3) / 2))))
        (= k 8) (infix (n * ((3 * n) - 2)))
        :else (throw (Exception. "invalid index"))))

(defn s [k]
  (->> (iterate inc 1)
       (map #(p k %))
       (drop-while #(< % 1000))
       (take-while #(< % 10000))))

(defn ps [k]
  (->> (s k)
       (map (fn [n] {:g k, :n n}))))

(defn first-2-digits [n] (quot n 100))
(defn last-2-digits [n] (rem n 100))

(defn chain [M ps]
  (for [e ps, t (M (last-2-digits (:n (last e))))] (conj e t)))

(defn p061 []
  (let [M (->> (range 3 9)
               (map ps)
               (apply concat)
               (group-by (fn [e] (first-2-digits (e :n)))))]
    (->> (chain M (map vector (ps 8)))
         (chain M)
         (chain M)
         (chain M)
         (chain M)
         (chain M)
         (filter #(= (first %) (last %))) ; check cycle
         (filter (fn [e] (= #{3 4 5 6 7 8} (set (map #(% :g) e)))))
         first rest
         (map #(:n %))
         (apply +))))

(defn solve []
  (time (p061)))
