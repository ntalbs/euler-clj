;; #028

(ns p028)

(defn square [n] (* n n))

; n=(1 2 3 4)라 할 때,
; i번째 테두리의 대각선 요소는 다음 식으로 구할 수 있음 (i >= 2)
; l(i) = (2i-2)n + (2i-3)^2
(defn l [i n]
  (+ (-> (* 2 i) (- 2) (* n)) (square (-> (* 2 i) (- 3)))))

(defn p028 [size]
  (let [s (for [i (range 2 (inc (/ (inc size) 2)))]
            (apply + (map (partial l i) (range 1 5))))]
    (+ 1 (apply + s))))

(defn solve []
  (time (println (p028 1001))))
