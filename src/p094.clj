(ns p094)

(def limit 1000000000)

(defn next-pair [[x y]]
  [(+' (*' 2 x) (*' 3 1 y))             ;next-x
   (+' (*' 2 y) (*' 1 x))])             ;next-y

(defn x-to-a [x]
  [(/ (+ (* 2 x) 1) 3)                  ;for case 1 (a, a, a+1)
   (/ (- (* 2 x) 1) 3)])                ;for case 2 (a, a, a-1)

(defn perimeter [x]
  [(+ (* 2 x) 2)                        ;for case 1 (a, a, a+1)
   (- (* 2 x) 2)])                      ;for case 2 (a, a, a-1)

(defn area [[x y]]
  [(* (/ (+ x 2) 3) y)                  ;for case 1 (a, a, a+1)
   (* (/ (- x 2) 3) y)])                ;for case 2 (a, a, a-1)

(defn side-area-perimeter [[x y]]
  (let [[s1 s2] (x-to-a x)
        [a1 a2] (area [x y])
        [p1 p2] (perimeter x)]
    [{:side s1 :area a1, :perimeter p1}
     {:side s2 :area a2, :perimeter p2}]))

(defn solve []
  (->> (iterate next-pair [2 1])
       (mapcat side-area-perimeter)
       (filter (fn [{s :side}] (integer? s)))
       (filter (fn [{a :area}] (and (< 0 a) (integer? a))))
       (take-while (fn [{p :perimeter}] (<= p limit)))
       (map (fn [{p :perimeter}] p))
       (apply +)))
