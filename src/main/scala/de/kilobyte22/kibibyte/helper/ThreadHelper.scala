package de.kilobyte22.kibibyte.helper

object ThreadHelper {
  def runThreaded(t: => Unit) =
    new Thread(new Runnable {
      def run() {
        t _
      }
    }).start()
}
