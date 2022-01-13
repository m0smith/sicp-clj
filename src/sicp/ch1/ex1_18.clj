(ns sicp.ch1.ex1-18
  ""
   (:refer-clojure :exclude [cond define * double])
   (:require [sicp.scheme.core :refer :all]))

(define (* a b)
  (if (= b 0)
      0
      (+ a (* a (- b 1)))))

(define (double x) (+ x x))

(define (halve x) (/ x 2))

(define (*-fast x y)
  (define (*-fast* a x y)
    (println a "+" x "*" y)
    (cond 
      ;;((= 1 x) (+ a y))
      ;;((= 1 y) (+ a x))
      ((= 0 x) a)
      ((= 0 y) a)
      ((< y x) (*-fast* a y x))
      ((even? x) (*-fast* a (halve x) (double y)))
      (else (*-fast* (+ a y) (dec x) y))))
  (*-fast* 0 x y))

;; Clojure

(defn *-fast [x y]
  (letfn
    [(*-fast*
       [a x y]
       (println a "+" x "*" y)
       (clojure.core/cond
         (= 0 x) a
         (= 0 y) a
         (< y x) (recur a y x)
         (even? x) (recur a (halve x) (double y))
         :else (recur (+ a y) (dec x) y)))]
    (*-fast* 0 x y)))
