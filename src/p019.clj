(ns p019
  (:import java.util.Calendar
           java.time.LocalDate
           java.time.DayOfWeek))

(defn solve1 []
  (let [cal (Calendar/getInstance)]
    (count (for [year (range 1901 2001)
                 month (range 0 12)
                 :when (= Calendar/SUNDAY (do (. cal set year month 1)
                                              (. cal get Calendar/DAY_OF_WEEK)))]
             [year month 1]))))

(defn solve2 []
  (let [base (LocalDate/of 1901 1 1)]
    (->> (map #(.plusMonths base %) (range))
         (take-while #(<= (.getYear %) 2000))
         (filter #(= (.getDayOfWeek %) DayOfWeek/SUNDAY))
         (count))))

(defn- divisible? [x n] (zero? (mod x n)))

(defn- leap-year? [year]
  (if (divisible? year 100)
    (divisible? year 400)
    (divisible? year 4)))

(defn- days-in-month [year month]
  {:pre [(<= 1 month 12)]}
  (cond (#{1 3 5 7 8 10 12} month) 31
        (#{4 6 9 11} month) 30
        (leap-year? year) 29
        :else 28))

(defn- next-date [[year month dm dw]]
  (let [last-day (days-in-month year month)
        next-dw (fn [dw] (mod (inc dw) 7))]
    (if (< dm last-day)
      [year month (inc dm) (next-dw dw)]
      (if (< month 12)
        [year (inc month) 1 (next-dw dw)]
        [(inc year) 1 1 (next-dw dw)]))))

(defn solve3 []
  (->> (iterate next-date [1900 1 1 1])
       (drop-while (fn [[year _ _ _]] (<= year 1900)))
       (take-while (fn [[year _ _ _]] (<= year 2000)))
       (filter (fn [[_ _ dm dw]] (and (= 1 dm) (zero? dw))))
       (count)))

(defn- next-month [[year month dm dw]]
  (let [dw (mod (+ dw (days-in-month year month)) 7)]
    (if (= month 12)
      [(inc year) 1 1 dw]
      [year (inc month) 1 dw])))

(defn solve4 []
  (->> (iterate next-month [1900 1 1 1])
       (drop-while (fn [[year _ _ _]] (<= year 1900)))
       (take-while (fn [[year _ _ _]] (<= year 2000)))
       (filter (fn [[_ _ dm dw]] (and (= 1 dm) (zero? dw))))
       (count)))

;; (do
;;   (time (print "1: " (solve1) "\t"))
;;   (time (print "2: " (solve2) "\t"))
;;   (time (print "3: " (solve3) "\t"))
;;   (time (print "4: " (solve4) "\t")))

(defn solve []
  (solve4))
