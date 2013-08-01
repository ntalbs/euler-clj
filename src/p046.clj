;; #046

(use '[clojure.contrib.lazy-seqs :only (primes)]
     '[clojure.set :only (difference)]
     '[util :only (prime?)])

(def limit 10000)

(def ps (take-while #(< % limit) primes))

(def odd-composite-nums
  (->> (range 3 limit 2)
       (filter #(not (prime? %)))
       (into #{})))

(def goldbach-nums
  (into #{} (for [p ps n (range 1 (Math/sqrt limit))] (+ p (* 2 n n)))))

(defn p046 []
  (apply min (difference odd-composite-nums goldbach-nums)))

(time (println (p046)))


       
