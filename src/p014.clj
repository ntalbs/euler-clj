;; #014

(defn x [n]
  (letfn [(x [n acc]
            (if (= 1 n)
              (conj acc 1)
              (if (even? n)
                (recur (quot n 2) (conj acc n))
                (recur (+ (* 3 n) 1) (conj acc n)))))]
    (x n [])))

(defn p014 []
  (->> (range 1 (inc 1000000))
       (map (fn [n] [n (count (x n))]))
       (apply max-key (fn [[n cnt]] cnt))))

(time (println (p014)))
