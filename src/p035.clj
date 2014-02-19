;; #035
;; How many circular primes are there below one million?

(use '[util :only (parse-int digits prime?)]
     '[clojure.contrib.lazy-seqs :only (primes)])

(defn circular-nums [n]
  (let [ds (digits n) len (count ds)]
    (->> (cycle ds)
         (partition len 1)
         (take len)
         (map #(parse-int (apply str %))))))

(defn all-prime? [coll]
  (every? prime? coll))

(defn p035 []
  (->> primes
       (take-while #(< % 1000000))
       (filter (fn [n] (all-prime? (circular-nums n))))
       count))

(time (println (p035)))
