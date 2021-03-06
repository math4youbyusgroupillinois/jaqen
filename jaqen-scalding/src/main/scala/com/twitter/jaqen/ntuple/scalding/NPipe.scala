package com.twitter.jaqen.ntuple.scalding

import scala.language.experimental.macros
import scala.language.implicitConversions

import com.twitter.jaqen.ntuple.NTuple
import com.twitter.scalding.typed.TypedPipe
import com.twitter.jaqen.ntuple.scalding.NPipeMacros._


object NPipe {

  implicit def TypedPipeOfNTupleToNPipe[T <: NTuple[T]](pipe: TypedPipe[T]) = NPipe[T](pipe)

}

case class NPipe[T] (val tpipe: TypedPipe[T]) {
  def nmap(pair: Any)(f: Any) = macro pipeMapImpl[T]
  def ndiscard(keys: Any*) = macro pipeDiscardImpl[T]
  def nproject(keys: Any*) = macro pipeProjectImpl[T]
}