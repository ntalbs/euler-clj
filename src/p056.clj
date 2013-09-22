;; #056

(use '[util :only (pow digits)])

(defn p056 []
  (apply max
         (for [a (range 1 100) b (range 1 100)]
           (->> (pow a b)
                (digits)
                (apply +)))))

(time (println (p056)))
