;; #074

(use '[util :only (digits factorial)])

(defn sum-of-factorial [n]
  (->> (digits n)
       (map factorial)
       (apply +)))

(def sum-of-factorial (memoize sum-of-factorial))

(defn count-steps [n]
  (letfn [(cnt-steps [s chain cnt]
            (let [x (sum-of-factorial s)]
              (if (contains? chain x) cnt
                  (recur x (conj chain x) (inc cnt)))))]
        (cnt-steps n #{n} 1)))

(defn p074 []
  (->> (range 1 (inc 1000000))
       (filter #(= 60 (count-steps %)))
       count))

; Takes about 25 secs. Need to be improved.
(time (println (p074)))
