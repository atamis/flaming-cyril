#!/usr/bin/env ruby

def label x
  print x + ": "
end

p1_wins = Hash.new(0)
p1_stalemates = Hash.new(0)
p1_games = Hash.new(0)

board_14_stalemates = 0
board_14_games = 0
games = 0
stalemates = 0

time_p_turn = Hash.new(0)

game_time = 0

game_time_sum = 0

moves_avg = 0

max = 0

max_string = ""

# [wins, stale, loss]
p1_wins_board = Hash.new(0)
p1_stale_board = Hash.new(0)
p1_loss_board = Hash.new(0)

games_board = Hash.new(0)

ARGF.each_with_index do |line, idx|
  ary = line.split(" ")
  next if ary == []
  #puts ([idx] + ary).inspect
  # Winner
  if ary[0] == "RED"
    won = :red
  elsif ary[0] == "BLACK"
    won = :black
  elsif ary[0] == "Stalemate."
    won = :stalemate
  else
    #puts "error"
    next
  end

  # Time
  if won == :stalemate
    time = ary[7]
  else
    time = ary[5]
  end

  # Moves
  if won == :stalemate
    moves = ary[10]
  else
    moves = ary[8]
  end

  # Player 1
  if won == :stalemate
    p1 = ary[12][0..-2]
    p1_color = ary[13]
  else
    p1 = ary[10][0..-2]
    p1_color = ary[11]
  end

  # Player 2
  if won == :stalemate
    p2 = ary[17][0..-2]
    p2_color = ary[18]
  else
    p2 = ary[15][0..-2]
    p2_color = ary[16]
  end
  
  board = ary.last

  # Analysis
  
  if board == "14"
    board_14_games += 1
    if won == :stalemate
      board_14_stalemates += 1
    end
  end

  games += 1
  stalemates += 1 if won == :stalemate

  if won == :black
    p1_wins[p1 + board] += 1
    p1_wins_board[board] += 1
  elsif won == :stalemate
    p1_stalemates[p1 + board] += 1
    p1_stale_board[board] += 1
  else
    p1_loss_board[board] += 1
  end
  p1_games[p1 + board] += 1

  if time.to_f > max
    max_string = line
    max = time.to_f
  end

  games_board[board] += 1

  time_p_turn[p1] = (time_p_turn[p1]*(games - 1) + time.to_f/moves.to_i) / games
  game_time = (game_time * (games - 1) + time.to_f)/games
  game_time_sum += time.to_f
  moves_avg = (moves_avg * (games - 1) + moves.to_i)/games
  
  # Need to remove commas from player names

  #puts [time, moves, p1, p2].inspect

  # won, stalemated, size, NaivePlayer, BCCPlayer, WCCPlayer, CWCCPlayer, NCCPlayer,
=begin
  puts [
    won == :black ? 1 : 0,
    won == :stalemate ? 1 : 0,
    board,
    p1 == "NaivePlayer" ? 1 : 0,
    p1 == "BCCPlayer" ? 1 : 0,
    p1 == "WCCPlayer" ? 1 : 0,
    p1 == "CWCCPlayer" ? 1 : 0,
    p1 == "NCCPlayer" ? 1 : 0
  ].join(",")
=end
  puts [
  won,
  time,
  moves,
  board
  ].join(",")
end

puts "                     Wins"

p1_wins.each do |k, v|
  puts k + ": " + (p1_wins[k].to_i.to_f / p1_games[k].to_i.to_f).to_s
end

puts "                     Stalemates"

p1_stalemates.each do |k, v|
  puts k + ": " + (p1_wins[k].to_i.to_f / p1_games[k].to_i.to_f).to_s + ", on " + p1_games[k].to_s + " games."
end

puts board_14_stalemates
puts board_14_games

label "Percentage of stalemates on board 14"
puts board_14_stalemates.to_f/board_14_games.to_f

label "Percentage of stalemates"
puts stalemates.to_f/games.to_f

label "Average time/turn"
puts time_p_turn.inspect

label "Average game time, way 1"
puts game_time
label "Average game time, way 2"
puts game_time_sum/games
label "Average moves per game"
puts moves_avg

puts "String for the longest game: "
puts max_string

label "Number of games"
puts games

puts "Board size wins"

p1_wins_board.each do |k, v|
  puts k + ": " + (v.to_f/games_board[k]).to_s
end

puts "Board size stale"

p1_stale_board.each do |k, v|
  puts k + ": " + (v.to_f/games_board[k]).to_s
end

puts "Board size loss"

p1_loss_board.each do |k, v|
  puts k + ": " + (v.to_f/games_board[k]).to_s
end
