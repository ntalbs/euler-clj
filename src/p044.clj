;; #044

(ns p044)

(defn p
  "Returns a n-th pentagonal number. P(n) = n (3n−1)/2"
  [n]
  (/ (* n (- (* 3 n) 1)) 2))

(def upper 10000) ;; 그냥 여기까지... 이거 이상 넘어가면 숫자가 너무 커서 결과 안 나옴

(def ps (->> (range 1 upper)
             (map #(p %))
             (into #{})))

; 근데 답이 나오네...
(defn p044 []
  (->> (for [i (range 1 upper) j (range 1 i)
             :when (not (nil? (ps (- (p i) (p j)))))
             :when (not (nil? (ps (+ (p i) (p j)))))]
         (- (p i) (p j)))
       (take 10)))

(defn solve []
  (time (println (p044))))
