;; #014

(ns p014)

(defn x [n]
  (loop [n n acc []]
    (if (= 1 n)
      (conj acc 1)
      (if (even? n)
        (recur (quot n 2) (conj acc n))
        (recur (+ (* 3 n) 1) (conj acc n))))))


(defn p014 []
  (->> (range 1 (inc 1000000))
       (map (fn [n] [n (count (x n))]))
       (apply max-key (fn [[n cnt]] cnt))))

(defn solve []
  (time (println (p014))))
