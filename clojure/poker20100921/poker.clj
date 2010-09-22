(ns poker)

(defn ranks
  [hand]
  (map first hand))

(defn suites
  [hand]
  (map second hand))

(defn number-of-same-ranks
  [hand number]
  (count (filter (partial = number) (vals (frequencies (ranks hand))))))

(defn number-of-pairs
  [hand]
  (number-of-same-ranks hand 2))

(defn one-pair?
  [hand]
  (= 1 (number-of-pairs hand)))

(defn two-pair?
   [hand]
   (= 2 (number-of-pairs hand)))
   
(defn three-of-a-kind?
   [hand]
   (= 1 (number-of-same-ranks hand 3)))

(defn second-one-greater-than-first?
  [[a b]]
  (= a (dec b)))

(defn ace-as-fourteen 
  [ranks]
  (replace {1 14} ranks))

(defn straight-in-a-row
  [ranks]
  (every? true? (map second-one-greater-than-first? (partition 2 1 (sort ranks)))))

(defn straight?
   [hand]
   (or (straight-in-a-row (ranks hand))
     (straight-in-a-row (ace-as-fourteen (ranks hand)))))

(defn flush?
  [hand]
  (= 1 (count (distinct (suites hand)))))
 
(defn full-house?
  [hand]
  (and (one-pair? hand) (three-of-a-kind? hand)))

(defn rank-hand
  [hand]
  (cond 
    (full-house? hand) :full-house
    (flush? hand) :flush
    (straight? hand) :straight
    (three-of-a-kind? hand) :three-of-a-kind 
    (two-pair? hand) :two-pair 
    (one-pair? hand) :one-pair
    :else :high-card))
