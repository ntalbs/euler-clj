(ns p019
  (:import java.util.Calendar))

(def cal (Calendar/getInstance))

(def sundays-fell-on-the-first-of-the-month-during-the-20th-century
  (for [year (range 1901 2001) month (range 0 12)
        :when (= Calendar/SUNDAY (do
                                   (. cal set year month 1)
                                   (. cal get Calendar/DAY_OF_WEEK)))]
         [year month 1]))

(defn p019 []
  (count sundays-fell-on-the-first-of-the-month-during-the-20th-century))

(defn solve []
  (time (println (p019))))
