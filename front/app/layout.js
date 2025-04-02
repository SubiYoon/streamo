import "./globals.css";

const RootLayout = ({ children }) => {
  return (
    <html lang="en">
      <body className={`mgr-0 pd-0`}>
        {children}
      </body>
    </html>
  )
}

export default RootLayout