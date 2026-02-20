interface = peripheral.find("crystal_interface")
while true do

function dial(address)
    local addressLength = #address
    local start = interface.getChevronsEngaged() + 1
    interface.rotateClockwise(-1)
    for chevron = start,addressLength,1 do
        local symbol = address[chevron]
        interface.engageSymbol(symbol)
        sleep(1)
    end 
    interface.endRotation()
end

function close()
    interface.engageSymbol(0)
    interface.rotateClockwise(0)
    while not interface.isCurrentSymbol(0) do
        sleep(0)
    end
    interface.endRotation()
end



abydosAddress = {26,6,14,31,11,29,0}

chulakAddress = {8,1,22,14,36,19,0}

lanteaAddress = {18,20,1,15,14,7,19,0}


print("Avaiting input:")

print("0 = stop")
print("1 = Abydos")
print("2 = Chulak")
print("3 = Lantea")

print("-1 = exit")


input = tonumber(io.read())
sleep(0)

if input == 0 then
    close()
elseif input == -1 then
    break
elseif input == 1 then
    dial(abydosAddress) --We're using the function we wrote earlier
elseif input == 2 then
    dial(chulakAddress)
elseif input == 3 then
    dial(lanteaAddress)
else
    print("Invalid input")
end
end
