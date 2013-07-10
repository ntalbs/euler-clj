;; #001
;; sum of all multiples of 3 and 5 below 1000.

; brute force.
; sufficiently fast when n is small, however, it will get slower when n get bigger.
(defn p001-1 []
  (->> (range 1000)
       (filter (fn [n] (or (= 0 (mod n 3)) (= 0 (mod n 5)))))
       (apply +)))

(time (println "brute force => " (p001-1)))

; using formula: s(n) = n(n+1)/2
; much faster method. The size of n does not affect the calculation time.
(defn s
  ([n]
     (/ (* n (+ n 1)) 2))
  ([n m]
     (* m (s (quot n m)))))

(defn p001-2 []
  (let [n (dec 1000)]
    (-> (s n 3)
        (+ (s n 5))
        (- (s n 15)))))

(time (println "using formula => " (p001-2)))  
