(ns p120)

(defn r-max [a]
  (* 2 a (quot (dec a) 2)))

(defn solve []
  (->> (range 3 (inc 1000))
       (map r-max)
       (apply +)))
