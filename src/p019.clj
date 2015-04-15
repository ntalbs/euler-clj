(ns p019
  (:import java.util.Calendar))

(defn solve1 []
  (let [cal (Calendar/getInstance)]
    (count (for [year (range 1901 2001)
                 month (range 0 12)
                 :when (= Calendar/SUNDAY (do (. cal set year month 1)
                                              (. cal get Calendar/DAY_OF_WEEK)))]
             [year month 1]))))

