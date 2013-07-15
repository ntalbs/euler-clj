;; #015
;; number of routes through 20x20 grid

(defn f
  "calculate factorial of n"
  [n]
  (->> (range 1 (inc n))
       (map #(java.math.BigInteger. (str %)))
       (apply *)))

(defn p015 []
  (let [f40 (f 40) f20 (f 20)]
    (/ f40 (* f20 f20))))

(time (println (p015)))
