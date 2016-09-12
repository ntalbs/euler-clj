;; Refer to the following URLs:
;; http://www.programminglogic.com/integer-partition-algorithm/
;; http://stackoverflow.com/questions/14053885/integer-partition-algorithm-and-recursion

(ns p076)

(defn p [n k]
  (cond (= k 0) 0
        (= n 0) 1
        (= n 1) 1                     ; (p 1 k) always goes to 1
        (< n 0) 0
        :else (+ (p n (dec k)) (p (- n k) k))))

(def p (memoize p))

(defn solve []
  (p 100 99))
