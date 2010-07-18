#!/usr/bin/ruby

# config
itr = 100000;
seq = [3]
start_rerolls = 2

# const
s = 0
f = 0

1.upto(itr) do |i|
	state = 1 # 1 = succes, 0 = fail
	rerolls = start_rerolls 
	seq.each do |n|
		r = 1 + rand(6)
		if r < n
			if rerolls > 0
				rerolls = rerolls - 1
				r = 1 + rand(6)
				if r < n
					state = 0
				end	
			else
				state = 0
			end
		end	
	end	

	if state == 1
		s = s +1
	else
		f = f +1
	end
end

puts "''''''''''''"
puts ((s.to_f / itr) * 100).to_s
