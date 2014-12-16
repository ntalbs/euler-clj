(ns p086
  (:require [util :refer [perfect-square?]]))

(defn cnt [M]
  (apply +
         (for [bc (range 3 (inc (* 2 M))) :when (perfect-square? (+ (* M M) (* bc bc)))]
           (if (<= bc M)
             (int (/ bc 2))
             (inc (- M (int (/ (+ bc 1) 2))))))))

(defn p086 [limit]
  (->> (iterate inc 3)
       (map (fn [x] [x (cnt x)]))
       (reductions (fn [[a1 a2] [b1 b2]] [b1 (+ a2 b2)]))
       (drop-while (fn [[M cnt]](<= cnt limit)))
       first))

(defn solve []
  (time (println (p086 1000000))))
