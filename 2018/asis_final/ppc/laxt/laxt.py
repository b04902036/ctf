'''
given a number, for example 2018
for each given number y, construct y with operation ' %&()*+-/<>^|~', and number [2, 0, 1, 8]
'''
def laxt(expr, num):
    ops = ' %&()*+-/<>^|~'
    nude = expr.translate(None, ops)
    try:
        flag, val = True, eval(expr)
    except:
        flag, val = False, None
    return set(nude) == set(num) and flag, val

