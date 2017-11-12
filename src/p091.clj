(ns p091)

(def LIMIT 50)

(def points
  (for [x (range 1 (inc LIMIT)) y (range 1 (inc LIMIT))]
    [x y]))

(defn fn-line-perpendicular-to [[a b]]
  (fn [x] (+ (/ (* (- a) x) b) b (/ (* a a) b))))

(defn solve []
  (->> points
       (map fn-line-perpendicular-to)
       (mapcat (fn [f] (->> (range (inc LIMIT))
                            (map f)
                            (filter integer?)
                            (filter #(<= 0 % 50)))))
       count
       (+ 2500)   ; P, Q가 각각 x축, y축에 있는 경우
       (+ 2500)   ; P는 x축, Q는 1사분면 안에 있는 경우
       (+ 2500))) ; P는 y축, Q는 1사분면 안에 있는 경우
