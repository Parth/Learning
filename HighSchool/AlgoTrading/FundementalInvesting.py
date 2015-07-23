import time
import urllib2
from urllib2 import urlopen

sp500short = ['a', 'aa', 'aapl', 'abbv', 'abc', 'abt', 'ace', 'aci', 'acn', 'act', 'adbe', 'adi', 'adm', 'adp']

def yahooKeyStats(stock):
	try:
		sourceCode = urllib2.urlopen('http://finance.yahoo.com/q/ks?s='+stock).read()
		pbr = sourceCode.split('Price/Book (mrq):</td><td class="yfnc_tabledata1">')[1].split('</td>')[0]
		if float(pbr) < .70
			print(stock+': '+pbr)
			PEG5 = sourceCode.split('PEG Ratio (5 yr expected)<font size="-1"><sup>1</sup></font>:,'
			5:55 of video 5
	except Exception,e :
		print('err: '+e)

for eachStock in sp500short:
	yahooKeyStats(eachStock)
