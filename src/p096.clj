;; https://news.ycombinator.com/item?id=4325746
;; https://gist.github.com/swannodette/3217582
;; https://gist.github.com/orb/5884956
;; http://en.wikipedia.org/wiki/Constraint_logic_programming
;; https://github.com/clojure/core.logic

(ns p096
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic])
  (:require [clojure.core.logic.fd :as fd]))

(defn- to-int [c] (Character/digit c 10))

(def puzzles
  (with-open [rdr (clojure.java.io/reader "data/sudoku.txt")]
    (->> (line-seq rdr)
         (partition 10)
         (map rest)
         (map (fn [ns] (mapcat #(map to-int %) ns)))
         (map vec)
         (doall))))

(defn init-board [vars puzzle]
  (matche [vars puzzle]
          ([[] []]
             succeed)
          ([[_ . more-vars] [0 . more-puzzle]]
             (init-board more-vars more-puzzle))
          ([[num . more-vars] [num . more-puzzle]]
             (init-board more-vars more-puzzle))))

(defn solve-puzzle [puzzle]
  (let [sdnum (fd/domain 1 2 3 4 5 6 7 8 9)
        board (repeatedly 81 lvar)
        rows (into [] (map vec (partition 9 board)))
        cols (apply map vector rows)

        get-square (fn [x y]
                     (for [x (range x (+ x 3)) y (range y (+ y 3))]
                       (get-in rows [x y])))

        squares (for [x (range 0 9 3) y (range 0 9 3)]
                  (get-square x y))]
    (run* [q]
         (== q board)
         (everyg #(fd/in % sdnum) board)
         (init-board board puzzle)
         (everyg fd/distinct rows)
         (everyg fd/distinct cols)
         (everyg fd/distinct squares))))

(defn solve []
  (->> (map solve-puzzle puzzles)
       (map #(take 3 (first %)))
       (map #(apply str %))
       (map #(Integer/parseInt %))
       (apply +)))
