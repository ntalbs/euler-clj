(ns p084)

(def board (atom (vec (repeat 40 0))))
(def pos (atom 0))
(def double-cnt (atom 0))
(def chi (atom 0))
(def cci (atom 0))

(defn- init []
  (reset! board (vec (repeat 40 0)))
  (reset! pos 0)
  (reset! double-cnt 0))

(defn- visit [n]
  (reset! pos n)
  (swap! board assoc n (inc (nth @board n))))

(defn- go-to [dest]
  (let [n ({:start 0, :jail 10, :c1 11, :e3 24, :h2 39, :r1 5} dest)]
    (visit n)))

(defn- forward [n]
  (let [p (mod (+ @pos n) 40)]
    (if (= 30 p)
      (go-to :jail)
      (visit p))))

(defn- next-pos [n dest]
  (cond (= dest :r) (condp = n 7 15 22 25 36 5)
        (= dest :u) (condp = n 7 12 22 28 36 12)))

(defn- roll []
  [(inc (rand-int 4)) (inc (rand-int 4))])

(defn- inc-index
  "increase the card index"
  [n]
  (mod (inc n) 16))

(defn- cc-instruction
  "community chest"
  [pos]
  (swap! cci inc-index)
  (condp = @cci
    1 #(go-to :start)
    2 #(go-to :jail)
    #(visit pos)))

(defn- chance-instruction
  "chance"
  [pos]
  (swap! chi inc-index)
  (condp = @chi
    1 #(go-to :start)
    2 #(go-to :jail)
    3 #(go-to :c1)
    4 #(go-to :e3)
    5 #(go-to :h2)
    6 #(go-to :r1)
    7 #(visit (next-pos pos :r))
    8 #(visit (next-pos pos :r))
    9 #(visit (next-pos pos :u))
    0 #(forward -3)
    #(visit pos)))

(defn- follow [instruction]
  (if instruction (instruction)))

(defn- roll-and-move []
  (let [[r1 r2] (roll) p (+ r1 r2 @pos)]
    (if (= r1 r2)
      (swap! double-cnt inc)
      (reset! double-cnt 0))
    (cond (= 3 @double-cnt) (go-to :jail)
          (#{2 17 33} p) (follow (cc-instruction p))
          (#{7 22 36} p) (follow (chance-instruction p))
          :else (forward (+ r1 r2)))))

(defn run [n]
  (->> (repeatedly n roll-and-move)
       (last)
       (map-indexed (fn [i v] [i v]))
       (sort-by second #(compare %2 %1))
       (take 3)
       (map first)
       (map #(format "%02d" %))
       (apply str)))

(defn simulate []
  (init)
  (run 1000000))

;; D1(16)과 R3(25)의 방문 확률의 차이가 크지 않아 1백만번을 돌려도 종종 D1이 이기는 경우가 있음
;; 1백만번 돌리는 시뮬레이션을 10번 수행해서 많이 나오는 쪽으로 판단
(defn solve []
  (->> (repeatedly 10 simulate)
       (group-by identity)
       (map (fn [[k xs]] [k (count xs)]))
       (sort-by second)
       (ffirst)))
