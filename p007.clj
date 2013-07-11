;; #007
(defn prime? [n]
  (cond (= n 1) false
        (< n 4) true                    ; 2, 3
        (even? n) false
        (< n 9) true                    ; 5, 7
        (= 0 (mod n 3)) false
        :else (empty?
               (take 1 (filter
                        #(= 0 (mod n %))
                        (range 3 (inc (int (Math/sqrt n))) 2))))))

(def p007 (last (take 10001 (filter prime? (iterate inc 2)))))

(time (println p007))
